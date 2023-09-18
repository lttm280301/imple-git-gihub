package models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class LoginData {
  public List<ValidationDataLogin> validationEmail,validationPass;


	
}
