package vl.data.collectionservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.Album;
import vl.data.collectionservice.entities.Post;
import vl.data.collectionservice.entities.Todo;
import vl.data.collectionservice.entities.user.Address;
import vl.data.collectionservice.entities.user.Company;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserBean {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String website;

    private CompanyBean company;

    private AddressBean address;

    private List<TodoBean> todos;

    private List<PostBean> posts;

    private List<AlbumBean> albums;

}
