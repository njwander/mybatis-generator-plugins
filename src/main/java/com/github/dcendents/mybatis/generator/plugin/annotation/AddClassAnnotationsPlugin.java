package com.github.dcendents.mybatis.generator.plugin.annotation;

import java.util.List;

import lombok.NoArgsConstructor;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * Mybatis generator plugin to add annotations at the class level.
 */
@NoArgsConstructor
public class AddClassAnnotationsPlugin extends PluginAdapter {
	public static final String ANNOTATION_CLASS = "annotationClass";
	public static final String ANNOTATION_STRING = "annotationString";

	private String annotationClass;
	private String annotationString;

	@Override
	public boolean validate(List<String> warnings) {
		annotationClass = properties.getProperty(ANNOTATION_CLASS);
		annotationString = properties.getProperty(ANNOTATION_STRING);

		String warning = "Property %s not set for plugin %s";
		if (!stringHasValue(annotationClass)) {
			warnings.add(String.format(warning, ANNOTATION_CLASS, this.getClass().getSimpleName()));
		}
		if (!stringHasValue(annotationString)) {
			warnings.add(String.format(warning, ANNOTATION_STRING, this.getClass().getSimpleName()));
		}

		return stringHasValue(annotationClass) && stringHasValue(annotationString);
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(annotationClass);
		topLevelClass.addAnnotation(annotationString);

		return true;
	}

}
