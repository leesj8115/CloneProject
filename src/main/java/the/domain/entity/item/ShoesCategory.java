package the.domain.entity.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShoesCategory {
    WALKING("워킹화"),
    RUNNING("러닝화"),
    CLIMBING("등산/트래킹화"),
    SNEAKERS("스니커즈"),
    SLIPPERS("샌들/슬리퍼"),
    SPORTS("스포츠화"),
    ETC("기타");

    final String title;
}
