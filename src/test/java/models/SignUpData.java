package models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class SignUpData {
  public List<ValidationDataSignUp> validationDataSearch, validationDataEmpty, validationNoResult;


	
}
