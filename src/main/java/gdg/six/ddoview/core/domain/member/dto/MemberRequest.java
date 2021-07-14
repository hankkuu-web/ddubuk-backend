package gdg.six.ddoview.core.domain.member.dto;

import gdg.six.ddoview.core.domain.company.Company;
import gdg.six.ddoview.core.domain.member.Member;
import gdg.six.ddoview.core.domain.member.MemberType;
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
        private String profilePhotoUrl;

        @NotNull
        private MemberType memberType;

        @Email
        private String email;

        @NotNull
        private CompanyRequest company;

        @Getter
        public static class CompanyRequest {

            private String companyName;

            private String profilePhotoUrl;
        }

        public Member toMember() {
            return Member.builder()
                    .memberType(this.memberType)
                    .name(this.memberName)
                    .profilePhotoUrl(this.profilePhotoUrl)
                    .build();
        }

        public Company toCompany() {
            return Company.builder()
                    .name(this.company.companyName)
                    .profilePhotoUrl(this.company.profilePhotoUrl)
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
