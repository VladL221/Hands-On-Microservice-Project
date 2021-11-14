package vl.data.collectionservice.beans;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.user.AppUser;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoBean {


    private Long userId;

    private Long id;

    private String title;

    private Boolean completed;


}
