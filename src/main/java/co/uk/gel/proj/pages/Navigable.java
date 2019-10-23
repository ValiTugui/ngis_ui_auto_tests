package co.uk.gel.proj.pages;

public interface Navigable {

    public void NavigateTo(String pageToNavigate);
    public void NavigateTo(String urlToNavigate , String pageToNavigate);
    public void NavigateTo(String urlToNavigate , String pageToNavigate, String userType);

}
