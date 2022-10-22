package mtapitest.objects;

import java.util.List;

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
public class TestReport {
	private int totalTestCount;
	private int passCount;
	private int failCount;
	private int skipCount;
	private double passPercent;
	private List<TestCaseResults> passed;
	private List<TestCaseResults> failed;
	private List<TestCaseResults> skipped;
}
