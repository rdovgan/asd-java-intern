package team.asd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.PartyDto;
import team.asd.entity.Party;
import team.asd.service.PartyService;
import team.asd.util.PartyUtil;

@RestController
@RequestMapping("/party")
public class PartyController {
	private final PartyService partyService = new PartyService(null);

	@GetMapping("/{id}")
	public ResponseEntity<Object> getPartyById(@PathVariable Integer id) {
		try {
			return new ResponseEntity<>(PartyUtil.convertToDto(partyService.readById(id)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/")
	public ResponseEntity<Object> createParty(@RequestBody PartyDto partyDto) {
		try {
			Party party = PartyUtil.convertToEntity(partyDto);
			partyService.create(party);
			return new ResponseEntity<>(PartyUtil.convertToDto(party), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/")
	public ResponseEntity<Object> updateParty(@RequestBody PartyDto partyDto) {
		try {
			Party party = PartyUtil.convertToEntity(partyDto);
			partyService.update(party);
			return new ResponseEntity<>(PartyUtil.convertToDto(party), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteParty(@PathVariable Integer id) {
		try {
			partyService.delete(id);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
