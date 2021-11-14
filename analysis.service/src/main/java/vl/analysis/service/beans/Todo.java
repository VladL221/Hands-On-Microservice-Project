package vl.analysis.service.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import vl.analysis.service.beans.user.AppUser;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    private Long userId;

    private Long id;

    private String title;

    private Boolean completed;

}
