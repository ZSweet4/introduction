import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class App {

    public static void main(String[] args) {
        try{
            //Create a document object and use JSoup to fetch the website
            Document doc = Jsoup.connect("https://www.codetriage.com/?language=Java").get();

            //With the document fetched, we use JSoup's title() method to fetch the title
            System.out.printf("Title: %s\n", doc.title());
            Elements repositories = doc.getElementsByClass("repo-item");
            /**
             * For each repository, extract the following information:
             * Title
             * Number of issues
             * Description
             * Full name on gitgub
             */
            for(Element repository : repositories){
                //Extract the title
                String repositoryTitle = repository.getElementsByClass("repo-item-title").text();

                //Extract the number of issues on the repository
                String repositoryIssues = repository.getElementsByClass("repo-item-issues").text();

                //Extract the description of the repository
                String repositoryDescription = repository.getElementsByClass("repo-item-description").text();

                //Get the full name of the repository
                String repositoryGithubName = repository.getElementsByClass("repo-item-full-name").text();

                //The repository full name contains brackets that we remove first before generation the valid Github links
                String repositoryGithubLink = "https//github.com/" + repositoryGithubName.replaceAll("[()]", "");

                //Format and print the information to the console
                System.out.println(repositoryTitle + " - " + repositoryIssues);
                System.out.println("\t" + repositoryDescription);
                System.out.println("\t" + repositoryGithubLink);
                System.out.println("\n");
            }

            //In case of any IO errors, we want the messages written to the console
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}