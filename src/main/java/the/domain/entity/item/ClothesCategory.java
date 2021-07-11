package the.domain.entity.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClothesCategory {
    PADDING("다운/패딩"),
    OUTER("아우터"),
    TRAINING("트레이닝"),
    BEST("베스트"),
    LONGSLEEVE("긴팔티셔츠"),
    SHORTSLEEVE("반팔티셔츠"),
    LONGPANTS("긴바지"),
    SHORTPANTS("반바지"),
    BEACHWEAR("비치웨어/수영복"),
    UNDERWEAR("언더웨어"),
    ETC("기타");

    final String title;
}
