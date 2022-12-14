package reception.cleanRoom;

import javafx.scene.control.Button;

import java.time.LocalDate;
import java.time.LocalTime;

public class Clean {
    private String roomID;
    private String type;
    private LocalTime outTime;
    private Button cleanBtn;

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public Button getCleanBtn() {
        return cleanBtn;
    }

    public void setCleanBtn(Button cleanBtn) {
        this.cleanBtn = cleanBtn;
    }

    public LocalTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalTime outTime) {
        this.outTime = outTime;
    }
}
