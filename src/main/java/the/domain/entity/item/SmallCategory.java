package the.domain.entity.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SmallCategory {
	
	// 신발의 소분류
	WALKING("신발", "워킹화"),
    RUNNING("신발", "러닝화"),
    CLIMBING("신발", "등산/트래킹화"),
    SNEAKERS("신발", "스니커즈"),
    SLIPPERS("신발", "샌들/슬리퍼"),
    SPORTS("신발", "스포츠화"),
    SHOES_ETC("신발", "기타"),
    
    // 의류의 소분류
    PADDING("의류", "다운/패딩"),
    OUTER("의류", "아우터"),
    TRAINING("의류", "트레이닝"),
    BEST("의류", "베스트"),
    LONGSLEEVE("의류", "긴팔티셔츠"),
    SHORTSLEEVE("의류", "반팔티셔츠"),
    LONGPANTS("의류", "긴바지"),
    SHORTPANTS("의류", "반바지"),
    BEACHWEAR("의류", "비치웨어/수영복"),
    UNDERWEAR("의류", "언더웨어"),
    CLOTHES_ETC("의류", "기타"),
    
    // 용품의 소분류
    BAG("용품", "가방"),
    HATnGLOVES("용품", "모자/장갑"),
    SOCKS("용품", "양말"),
    CAMPING("용품", "등산/캠핑"),
    GOLF("용품", "골프"),
    FITNESS("용품", "피트니스"),
    LIFESPORT("용품", "생활스포츠"),
    BIKE("용품", "바이크"),
    SUPPLIES_ETC("용품", "기타");
	
    
    final String large;
    final String title;
}
