package cu.edu.cujae.pweb.bean.initCourse;

import cu.edu.cujae.pweb.dto.*;
import cu.edu.cujae.pweb.service.CourseService;
import cu.edu.cujae.pweb.service.SubjectInCourseService;
import cu.edu.cujae.pweb.service.YearService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageSubjectInYearBean {

	private SubjectInCourseCompleteDTO subjectInCourseDTO;
	private SubjectInCourseCompleteDTO selectedSubjectInCourse;
	private List<SubjectInCourseCompleteDTO> subjectsInCourse;
	private List<SubjectInCourseCompleteDTO> selectedSubjectsInCourse;
	private List<YearDTO> years;

	private List<List<SubjectInCourseCompleteDTO>> subjectsInCourseList;
	private Integer currentIndex;


	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private SubjectInCourseService subjectInCourseService;

	@Autowired
	private YearService yearService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ManageSubjectBean manageSubjectBean;


	public ManageSubjectInYearBean() {
		init();
	}
	private void init() {
		selectedSubjectInCourse = new SubjectInCourseCompleteDTO();
		subjectsInCourse = new ArrayList<>();
		subjectsInCourseList = new ArrayList<>();
		subjectsInCourseList.add(new ArrayList<>());
		subjectsInCourseList.add(new ArrayList<>());
		subjectsInCourseList.add(new ArrayList<>());
		subjectsInCourseList.add(new ArrayList<>());
		currentIndex = 0;
	}

	public boolean isCorrect(){
		for(List<SubjectInCourseCompleteDTO> subjectsInCourse: subjectsInCourseList)
			if(subjectsInCourse.size() <= 0)
				return false;
		return true;
	}

	public void refresh(){
		this.subjectsInCourse.clear();
		this.subjectsInCourseList.get(0).forEach(element -> subjectsInCourse.add(element));
		this.currentIndex = 0;
	}

	public String index(List<SubjectInCourseCompleteDTO> year){
		for(int i=0; i<subjectsInCourseList.size(); i++){
			if(subjectsInCourseList.get(i) == year){
				return i+"";
			}
		}

		return -1+"";
	}

	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedSubjectInCourse = new SubjectInCourseCompleteDTO();
		PrimeFaces.current().ajax().update("formSubjectInCourse");
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
		PrimeFaces.current().ajax().update("formSubjectInCourse");
//		List<RoleDto> roles = this.selectedUser.getRoles();
//		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
//		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}

	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveSubjectInCourse() {



		JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_subject_edited");


        PrimeFaces.current().executeScript("PF('manageSubjectInCourseDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("formSelectionSubject:tabContent");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

	public void changeYear(TabChangeEvent event){
		this.selectedSubjectInCourse = new SubjectInCourseCompleteDTO();
		int index = Integer.parseInt(event.getTab().getTitle().substring(event.getTab().getTitle().length()-1)) -1;

		this.subjectsInCourse.clear();
		this.subjectsInCourseList.get(index).forEach(element -> subjectsInCourse.add(element));
		this.currentIndex = index;
		PrimeFaces.current().ajax().update("formSelectionSubject:tabContent");
	}

	public boolean hasSelectedSubjectsInCourse() {
		return this.selectedSubjectsInCourse != null && !this.selectedSubjectsInCourse.isEmpty();
	}

	public void subjectRemove() {
		List<SubjectDTO> subjectsToRemove = new ArrayList<SubjectDTO>();
		System.out.println("1 size: "+selectedSubjectsInCourse.size());
		for (SubjectInCourseCompleteDTO s:selectedSubjectsInCourse) {
			for (int i=0; i < subjectsInCourse.size(); i++) {
				if(subjectsInCourse.get(i).getSubjectDTO().getId() == s.getSubjectDTO().getId()){
					subjectsToRemove.add(s.getSubjectDTO());
					subjectsInCourse.remove(i);
					subjectsInCourseList.get(currentIndex).remove(i);
					break;
				}
			}
		}
		setSelectedSubjectsInCourse(new ArrayList<SubjectInCourseCompleteDTO>());
		manageSubjectBean.subjectRemove(subjectsToRemove);

	}

    public void subjectAssign(List<SubjectDTO> subjectsToAssign) {
		List<CourseDTO> courses = courseService.getCourses();
		CourseDTO courseDTO = courses.get(courses.size()-1);
		subjectsToAssign.forEach(element-> {subjectsInCourseList.get(currentIndex).add(new SubjectInCourseCompleteDTO(courseDTO, (currentIndex+1),72 , element));});
		subjectsInCourse.clear();
		subjectsInCourseList.get(currentIndex).forEach(element->subjectsInCourse.add(element));
//		PrimeFaces.current().ajax().update("formSelectionSubject");
//		PrimeFaces.current().executeScript("alert('see');");
//		PrimeFaces.current().executeScript("PF('dtSubjectsInCourse').clearSelection();");
		PrimeFaces.current().ajax().update("formSelectionSubject");

	}

	public List<List<SubjectInCourseCompleteDTO>> getSubjectsInCourseList() {
		if (subjectsInCourseList == null) {
			subjectsInCourseList = new ArrayList<List<SubjectInCourseCompleteDTO>>();
			for (YearDTO y : yearService.getYears()){
				subjectsInCourseList.add(new ArrayList<SubjectInCourseCompleteDTO>());
			}
		}

		return subjectsInCourseList;
	}

	public YearService getYearService() {
		return yearService;
	}

	public void setYearService(YearService yearService) {
		this.yearService = yearService;
	}

	public void setSubjectsInCourseList(List<List<SubjectInCourseCompleteDTO>> subjectsInCourseList) {
		this.subjectsInCourseList = subjectsInCourseList;
	}

	public Integer getCurrentIndex() {
		return currentIndex == null?currentIndex = 0:currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public ManageSubjectBean getManageSubjectBean() {
		return manageSubjectBean;
	}

	public void setManageSubjectBean(ManageSubjectBean manageSubjectBean) {
		this.manageSubjectBean = manageSubjectBean;
	}

	public SubjectInCourseCompleteDTO getSubjectInCourseDTO() {
		return subjectInCourseDTO;
	}

	public void setSubjectInCourseDTO(SubjectInCourseCompleteDTO subjectInCourseDTO) {
		this.subjectInCourseDTO = subjectInCourseDTO;
	}

	public SubjectInCourseCompleteDTO getSelectedSubjectInCourse() {
		 return selectedSubjectInCourse==null?selectedSubjectInCourse = new SubjectInCourseCompleteDTO():selectedSubjectInCourse;
	}

	public void setSelectedSubjectInCourse(SubjectInCourseCompleteDTO selectedSubjectInCourse) {
		this.selectedSubjectInCourse = selectedSubjectInCourse;
	}

	public List<SubjectInCourseCompleteDTO> getSubjectsInCourse() {
		return subjectsInCourse==null?subjectsInCourse = new ArrayList<SubjectInCourseCompleteDTO>():subjectsInCourse;
	}

	public void setSubjectsInCourse(List<SubjectInCourseCompleteDTO> subjectsInCourse) {
		this.subjectsInCourse = subjectsInCourse;
	}

	public List<SubjectInCourseCompleteDTO> getSelectedSubjectsInCourse() {
		return selectedSubjectsInCourse;
	}

	public void setSelectedSubjectsInCourse(List<SubjectInCourseCompleteDTO> selectedSubjectsInCourse) {
		this.selectedSubjectsInCourse = selectedSubjectsInCourse;
	}

	public List<YearDTO> getYears() {
		return years == null ? yearService.getYears() : years;
	}

	public void setYears(List<YearDTO> years) {
		this.years = years;
	}

}
