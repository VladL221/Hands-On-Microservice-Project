package vl.analysis.service.service;

import vl.analysis.service.beans.Album;
import vl.analysis.service.beans.Post;
import vl.analysis.service.beans.Todo;
import vl.analysis.service.beans.user.AppUser;

import java.util.List;

public interface AnalysisService {

    List<Todo> getAllUncompletedTodos();

    List<Todo> getAllUncompletedTodosOfUser(Long userId);

    List<Post> getAllPostsWithoutComments(Long userId);

    List<AppUser> getAllUserAlbumsPhotosWithAmountBiggerThanThresholdAsUsers(Long threshold);

    List<Album> getAllUserAlbumsPhotosWithAmountBiggerThanThresholdAsAlbums(Long threshold);

}
