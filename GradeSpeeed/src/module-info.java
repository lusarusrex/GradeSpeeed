module imtrying {
	requires javafx.controls;
	requires javafx.fxml;
	exports application;
	opens application.controller to javafx.graphics, javafx.fxml;
}
