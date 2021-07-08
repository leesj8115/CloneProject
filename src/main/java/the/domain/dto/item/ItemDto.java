package the.domain.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ItemDto {
	private String largeCategory;
	private String brand;
	private String name;
	private long price;
	private long salePrice;
	// 파일은 별도로 관리
}
