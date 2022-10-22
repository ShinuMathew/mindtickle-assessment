package mtapitest.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.lang3.ObjectUtils;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import mtapitest.framework.MTTestFramework;

public class TestTransformerListener extends MTTestFramework implements IAnnotationTransformer {

	public TestTransformerListener() {
		this.getAllSectedTestCaseIds();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		if (!ObjectUtils.isEmpty(this.testCases)) {
			if (this.testCases.contains(annotation.getDescription()))
				annotation.setEnabled(true);
			else
				annotation.setEnabled(false);
		}
	}

}
