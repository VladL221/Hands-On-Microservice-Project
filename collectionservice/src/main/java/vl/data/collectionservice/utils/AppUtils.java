package vl.data.collectionservice.utils;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import vl.data.collectionservice.beans.*;
import vl.data.collectionservice.entities.*;
import vl.data.collectionservice.entities.user.Address;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.entities.user.Company;
import vl.data.collectionservice.entities.user.GeoLocation;
import vl.data.collectionservice.repo.AppUserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppUtils {



    public static TodoBean todoEntityToDto(Todo todo){
        TodoBean todoBean = new TodoBean();
        todoBean.setUserId(todo.getUserId().getId());
        todoBean.setId(todo.getId());
        todoBean.setTitle(todo.getTitle());
        todoBean.setCompleted(todo.getCompleted());
        return todoBean;
    }

    public static PostBean postEntityToDto(Post post){
        PostBean postBean = new PostBean();
        postBean.setUserId(post.getUserId().getId());
        postBean.setId(post.getId());
        postBean.setTitle(post.getTitle());
        postBean.setBody(post.getBody());
        List<Comment> comments = post.getComments();
        List<CommentBean> commentBeanList = comments.stream().map(AppUtils::commentEntityToDto).collect(Collectors.toList());
        postBean.setComments(commentBeanList);
        return postBean;
    }

    public static CommentBean commentEntityToDto(Comment comment){
        CommentBean commentBean = new CommentBean();
        commentBean.setPostId(comment.getPostId().getId());
        commentBean.setId(comment.getId());
        commentBean.setEmail(comment.getEmail());
        commentBean.setBody(comment.getBody());
        commentBean.setName(comment.getName());
        return commentBean;
    }

    private static CompanyBean companyEntityToDto(Company company){
        CompanyBean companyBean = new CompanyBean();
        companyBean.setName(company.getName());
        companyBean.setCatchPhrase(company.getCatchPhrase());
        companyBean.setBs(company.getBs());
        return companyBean;
    }

    public static AppUserBean appUserEntityToDto(AppUser appUser){
        AppUserBean appUserBean = new AppUserBean();
        appUserBean.setId(appUser.getId());
        appUserBean.setName(appUser.getName());
        appUserBean.setUsername(appUser.getUsername());
        appUserBean.setEmail(appUser.getEmail());
        appUserBean.setPhone(appUser.getPhone());
        appUserBean.setWebsite(appUser.getWebsite());

        CompanyBean companyBean = AppUtils.companyEntityToDto(appUser.getCompany());
        appUserBean.setCompany(companyBean);
        AddressBean addressBean = AppUtils.addressEntityToDto(appUser.getAddress());
        appUserBean.setAddress(addressBean);
        List<Post> posts = appUser.getPosts();
        List<PostBean> postBeanList = posts.stream().map(AppUtils::postEntityToDto).collect(Collectors.toList());
        appUserBean.setPosts(postBeanList);
        List<Todo> todos = appUser.getTodos();
        List<TodoBean> todoBeanList = todos.stream().map(AppUtils::todoEntityToDto).collect(Collectors.toList());
        appUserBean.setTodos(todoBeanList);
        List<Album> albums = appUser.getAlbums();
        List<AlbumBean> albumBeans = albums.stream().map(AppUtils::albumEntityToDto).collect(Collectors.toList());
        appUserBean.setAlbums(albumBeans);
        return appUserBean;
    }

    public static PhotoBean photoEntityToDto(Photo photo){
        PhotoBean photoBean = new PhotoBean();
        photoBean.setId(photo.getId());
        photoBean.setAlbumId(photo.getAlbumId().getId());
        photoBean.setTitle(photo.getTitle());
        photoBean.setUrl(photo.getUrl());
        photoBean.setThumbnailUrl(photo.getThumbnailUrl());
        return photoBean;
    }

    public static  AlbumBean albumEntityToDto(Album album){
        AlbumBean albumBean = new AlbumBean();
        albumBean.setUserId(album.getUserId().getId());
        albumBean.setId(album.getId());
        albumBean.setTitle(album.getTitle());
        List<Photo> photos = album.getPhotos();
        List<PhotoBean> photoBeanList = photos.stream().map(AppUtils::photoEntityToDto).collect(Collectors.toList());
        albumBean.setPhotos(photoBeanList);
        return albumBean;
    }

    private static GeoLocationBean geoLocationEntityToDto(GeoLocation geoLocation){
        GeoLocationBean geoLocationBean = new GeoLocationBean();
        geoLocationBean.setLat(geoLocation.getLat());
        geoLocationBean.setLng(geoLocation.getLng());
        return geoLocationBean;
    }

    private static AddressBean addressEntityToDto(Address address){
        AddressBean addressBean = new AddressBean();
        addressBean.setCity(address.getCity());
        addressBean.setStreet(address.getStreet());
        addressBean.setSuite(address.getSuite());
        addressBean.setZipcode(address.getZipcode());
        GeoLocationBean geoLocationBean = AppUtils.geoLocationEntityToDto(address.getGeo());
        addressBean.setGeo(geoLocationBean);
        return addressBean;
    }



}
