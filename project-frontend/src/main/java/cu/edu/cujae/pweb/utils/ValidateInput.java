package cu.edu.cujae.pweb.utils;

import javax.faces.application.FacesMessage;

import cu.edu.cujae.pweb.dto.StudentDTO;

public class ValidateInput {
	
	public static boolean validateStudent(StudentDTO student) {
		boolean valid = true;
		if(student.getIdNum().length() != 11) {
			JsfUtils.addMessageFromBundle("idNum", FacesMessage.SEVERITY_ERROR, "error_message_input_length_not_11");
			valid = false;
		}
		if(!isAlpha(student.getFirstName())) {
			JsfUtils.addMessageFromBundle("firstName", FacesMessage.SEVERITY_ERROR, "error_message_input_not_text");
			valid = false;
		}
		if(!isAlpha(student.getLastName())) {
			JsfUtils.addMessageFromBundle("lastName", FacesMessage.SEVERITY_ERROR, "error_message_input_not_text");
			valid = false;
		}
		
		return valid;
	}
	
    public static boolean isAlpha(String text){
        for(int i=0; i<text.length(); i++)
            if(!Character.isAlphabetic(text.charAt(i)) && !(text.charAt(i) == ' '))
                return false;
        return true;
    }
}
