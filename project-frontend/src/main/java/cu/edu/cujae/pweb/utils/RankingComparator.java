package cu.edu.cujae.pweb.utils;

import cu.edu.cujae.pweb.dto.StudentRankingDTO;

import java.util.Comparator;

public class RankingComparator implements Comparator<StudentRankingDTO> {

    @Override
    public int compare(StudentRankingDTO sr1, StudentRankingDTO sr2) {
        if(sr1.getAverage() > sr2.getAverage())
            return -1;
        else
            return 1;
    }
}
