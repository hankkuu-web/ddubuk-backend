package gdg.six.ddoview.core.domain.company.dto;

import gdg.six.ddoview.core.domain.company.Question;
import lombok.Builder;
import lombok.Getter;

@Getter
public class QuestionResponse {

    private long id;

    private String content;

    @Builder
    public QuestionResponse(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
    }
}
