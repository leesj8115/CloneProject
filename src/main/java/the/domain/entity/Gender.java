package the.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("M"),
    FEMALE("W"),
    NONE("N");

    final String gen;
}
