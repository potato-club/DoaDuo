
package gamza.project.doaduo.dto;

import lombok.Data;


public class AcceptStateDTO {
  private boolean acceptState;

  public AcceptStateDTO(boolean acceptState) {
    this.acceptState = acceptState;
  }

  public boolean isAcceptState() {
    return acceptState;
  }

  public void setAcceptState(boolean acceptState) {
    this.acceptState = acceptState;
  }
}
