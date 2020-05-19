package application;

public class UserLibrary {
	
	public static User currentUser;
	public static Integer currentMovieId;
	
	public static User getCurrentUser()
	{
		return currentUser;
	}
	
	public static void setCurrentUsername(User user)
	{
		 currentUser = user;
	}
	
	public static void setCurrentMovieId(Integer movieId)
	{
		currentMovieId = movieId;
	}
	
	public static Integer getCurrentMovieId()
	{
		return currentMovieId;
	}

}
