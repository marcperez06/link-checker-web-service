package io.github.marcperez06.link_checker_web_service.validators;

import java.util.ArrayList;
import java.util.List;

import io.github.marcperez06.java_utilities.collection.list.ListUtils;
import io.github.marcperez06.java_utilities.numbers.NumberUtils;
import io.github.marcperez06.java_utilities.validation.ValidationUtils;
import io.github.marcperez06.link_checker.report.configuration.LinkCheckerConfiguration;
import io.github.marcperez06.link_checker_web_service.domain.api_params.LinkCheckerParams;
import io.github.marcperez06.link_checker_web_service.domain.errors.LinkCheckerError;

public class LinkCheckerParamsValidator {

	public static List<LinkCheckerError> validate(LinkCheckerParams params) {
		List<LinkCheckerError> errors = new ArrayList<LinkCheckerError>();
		LinkCheckerConfiguration config = params.getConfiguration();
		LinkCheckerError urlError = checkUrl(params.getUrl());
		LinkCheckerError minDepthError = checkMinDepth(config.getMinDepth());
		LinkCheckerError minRequestsError = checkMinRequests(config.getMinRequests());
		LinkCheckerError minInteractionsError = checkMinInteractions(config.getMinInteractions());
		LinkCheckerError numThreadsError = checkNumThreads(config.getNumThreads());
		
		ListUtils.addObjectInListExceptsEmpty(errors, urlError);
		ListUtils.addObjectInListExceptsEmpty(errors, minDepthError);
		ListUtils.addObjectInListExceptsEmpty(errors, minRequestsError);
		ListUtils.addObjectInListExceptsEmpty(errors, minInteractionsError);
		ListUtils.addObjectInListExceptsEmpty(errors, numThreadsError);
		
		return errors;
	}
	
	private static LinkCheckerError checkUrl(String url) {
		LinkCheckerError error = null;
		if (ValidationUtils.isEmpty(url)) {
			error = new LinkCheckerError("linkToCheck", "Link to check can not be empty");
		}
		return error;
	}
	
	private static LinkCheckerError checkMinDepth(Integer minDepth) {
		return checkNumber("minDepth", minDepth, Integer.valueOf(99));
	}
	
	private static LinkCheckerError checkMinInteractions(Integer minInteractions) {
		return checkNumber("minInteractions", minInteractions, Integer.valueOf(9999));
	}
	
	private static LinkCheckerError checkMinRequests(Integer minRequests) {
		return checkNumber("minRequests", minRequests, Integer.valueOf(9999));
	}
	
	private static LinkCheckerError checkNumThreads(Integer numThreads) {
		return checkNumber("numThreads", numThreads, Integer.valueOf(10));
	}
	
	private static LinkCheckerError checkNumber(String field, Integer value, Integer maxNumber) {
		LinkCheckerError error = null;
		if (ValidationUtils.isNotNull(value)) {
			if (ValidationUtils.isNaN(value)) {
				error = new LinkCheckerError(field, "is not a number");
			} else if (NumberUtils.isLower(value, 0)) {
				error = new LinkCheckerError(field, "is lower than number allowed");
			} else if (NumberUtils.isBigger(value, maxNumber)) {
				error = new LinkCheckerError(field, "limit exceed");
			}	
		}
		return error;
	}
	
}