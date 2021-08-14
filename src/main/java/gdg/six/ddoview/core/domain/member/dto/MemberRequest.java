package gdg.six.ddoview.core.domain.member.dto;

import gdg.six.ddoview.core.domain.goods.Goods;
import gdg.six.ddoview.core.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MemberRequest {

    @Getter
    public static class CreateMember {

        @NotBlank
        private String memberName;

        @NotBlank
        private String phoneNumber;

        @Email
        private String email;

        public Member toMember() {
            return Member.builder()
                    .email(this.email)
                    .phoneNumber(this.phoneNumber)
                    .name(this.memberName)
                    .build();
        }
    }

    @Getter
    public static class CreateMemberResponse {
        private long memberId;
        @Builder
        public CreateMemberResponse(long memberId) {
            this.memberId = memberId;
        }
    }






}
