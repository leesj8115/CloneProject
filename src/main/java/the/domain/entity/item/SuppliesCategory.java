package the.domain.entity.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuppliesCategory {
    BAG("가방"),
    HATnGLOVES("모자/장갑"),
    SOCKS("양말"),
    CAMPING("등산/캠핑"),
    GOLF("골프"),
    FITNESS("피트니스"),
    LIFESPORT("생활스포츠"),
    BIKE("바이크"),
    ETC("기타");

    final String title;
}
