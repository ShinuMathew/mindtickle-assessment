package mtapitest.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestCaseResults {
	private int totalTestCount;
	private int passCount;
	private int failCount;
	private int skipCount;
	private double passPercent;
	
}
