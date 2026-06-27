package edu.wgu.d387_sample_code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.*;

@SpringBootApplication
public class D387SampleCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);

		// Updated for B3a & B3b rubric
		displayPresentationTimes();
	}

	// Displays the time of an online live presentation in ET, MT, and UTC
	public static void displayPresentationTimes() {

		// Example: Presentation scheduled for 2:00 PM Eastern Time
		LocalDateTime presentationTime = LocalDateTime.of(2026, 5, 5, 14, 0);

		ZonedDateTime etTime = presentationTime.atZone(ZoneId.of("America/Toronto")); // ET
		ZonedDateTime mtTime = etTime.withZoneSameInstant(ZoneId.of("America/Denver")); // MT
		ZonedDateTime utcTime = etTime.withZoneSameInstant(ZoneId.of("UTC")); // UTC

		java.time.format.DateTimeFormatter formatter =
				java.time.format.DateTimeFormatter.ofPattern("HH:mm");

		System.out.println("Online Live Presentation Times:");
		System.out.println("ET: " + etTime.format(formatter));
		System.out.println("MT: " + mtTime.format(formatter));
		System.out.println("UTC: " + utcTime.format(formatter));
	}
}
