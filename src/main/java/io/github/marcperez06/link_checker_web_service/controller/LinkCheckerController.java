package io.github.marcperez06.link_checker_web_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.marcperez06.java_utilities.json.GsonUtils;
import io.github.marcperez06.java_utilities.validation.ValidationUtils;
import io.github.marcperez06.link_checker.report.LinkCheckerReport;
import io.github.marcperez06.link_checker.report.services.LinkCheckerService;
import io.github.marcperez06.link_checker_web_service.domain.api_params.LinkCheckerParams;
import io.github.marcperez06.link_checker_web_service.domain.errors.LinkCheckerError;
import io.github.marcperez06.link_checker_web_service.validators.LinkCheckerParamsValidator;

@RestController
public class LinkCheckerController {

	@PostMapping(path = "/validation", 
					consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> validateBody(@RequestBody LinkCheckerParams params) {
		ResponseEntity<String> response = new ResponseEntity("{}", HttpStatus.OK);
		List<LinkCheckerError> errors = LinkCheckerParamsValidator.validate(params);
		if (ValidationUtils.isNotEmpty(errors)) {
			response = new ResponseEntity(GsonUtils.getJSON(errors), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping(path = "/report", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getReport(@RequestBody LinkCheckerParams params) {
		ResponseEntity<String> response = null;
		LinkCheckerReport report = null;
		List<LinkCheckerError> errors = LinkCheckerParamsValidator.validate(params);
		if (ValidationUtils.isEmpty(errors)) {
			report = LinkCheckerService.getReport(params.getUrl(), params.getConfigurationWithoutEmptyMinDepth());
			response = new ResponseEntity(GsonUtils.getJSON(report), HttpStatus.OK);
		} else {
			response = new ResponseEntity(GsonUtils.getJSON(errors), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
}
