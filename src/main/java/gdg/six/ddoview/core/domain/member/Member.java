package gdg.six.ddoview.core.domain.member;

import gdg.six.ddoview.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String profilePhotoUrl;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private long companyId = 0;

    @Builder
    public Member(String name, String profilePhotoUrl, MemberType memberType) {
        this.name = name;
        this.profilePhotoUrl = profilePhotoUrl;
        this.memberType = memberType;
    }

    public void updateCompanyId(long companyId) {
        this.companyId = companyId;
    }
}
