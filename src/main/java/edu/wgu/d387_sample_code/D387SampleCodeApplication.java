package edu.wgu.d387_sample_code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class D387SampleCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);

		// update today
		displayPresentationTimes();
	}

	// update today
	public static void displayPresentationTimes() {

		java.time.ZonedDateTime etTime =
				java.time.ZonedDateTime.now(java.time.ZoneId.of("America/New_York"));

		java.time.ZonedDateTime mtTime =
				etTime.withZoneSameInstant(java.time.ZoneId.of("America/Denver"));

		java.time.ZonedDateTime utcTime =
				etTime.withZoneSameInstant(java.time.ZoneId.of("UTC"));

		java.time.format.DateTimeFormatter formatter =
				java.time.format.DateTimeFormatter.ofPattern("HH:mm");

		System.out.println("Live Presentation Times:");
		System.out.println("ET: " + etTime.format(formatter));
		System.out.println("MT: " + mtTime.format(formatter));
		System.out.println("UTC: " + utcTime.format(formatter));
	}
}