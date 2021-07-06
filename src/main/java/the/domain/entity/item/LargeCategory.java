package the.domain.entity.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LargeCategory {
    SHOES("신발"),
    CLOTHES("의류"),
    SUPPLIES("용품");

    final String title;
}
