package com.vitaly.hibernatepostgrescrudapp.view;
//  14-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.controller.LabelController;
import com.vitaly.hibernatepostgrescrudapp.controller.PostController;
import com.vitaly.hibernatepostgrescrudapp.controller.WriterController;
import com.vitaly.hibernatepostgrescrudapp.model.Label;
import com.vitaly.hibernatepostgrescrudapp.model.Post;
import com.vitaly.hibernatepostgrescrudapp.model.PostStatus;
import com.vitaly.hibernatepostgrescrudapp.model.Writer;

import java.text.SimpleDateFormat;
import java.util.*;

public class PostView extends AbstractView{
    public PostView() {
        super("Post menu\n" +
                "1 - Create post\n" +
                "2 - Update post\n" +
                "3 - Delete post\n" +
                "4 - Read posts\n" +
                "5 - Exit post menu\n" +
                "Enter your choice: ");
    }

    private final PostController postController = new PostController();
    private final LabelController labelController = new LabelController();
    private final WriterController writerController = new WriterController();
    private final Scanner scanner = getScanner();
    private static final String PRINT_POST_LIST = "List of posts:\n";
    private static final String CREATE_POST_MSG = "Creating post.\n" + "Enter post content: ";
    private static final String EDIT_POST_MSG = "Enter ID of post to edit:";
    private static final String DELETE_POST_MSG = "Enter ID of post to delete: ";


    @Override
    public void create() {
        Post createdPost = new Post();
        System.out.println(CREATE_POST_MSG);
        createdPost.setPostStatus(PostStatus.ACTIVE);
        String content = scanner.nextLine();
        createdPost.setContent(content);
        createdPost.setCreated(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
        createdPost.setUpdated("NEW");

        List<Label> postLabels = labelController.getLabels();
            if(postLabels != null){
                postLabels.sort(Comparator.comparing(Label::getId));
                postLabels.forEach(System.out::println);
        }
        System.out.println("Enter ID of labels to add to the post:");
            List<Label> labels = new ArrayList<>();
            addLabelsToPost(labels);
        createdPost.setPostLabels(labels);

        List<Writer> writers = writerController.getWriters();
            if (writers != null) {
            writers.sort(Comparator.comparing(Writer::getId));
            writers.forEach(System.out::println);
            }
        System.out.println("Enter writer ID: ");
        Integer writerId = Integer.parseInt(scanner.nextLine());
        Writer writer = writerController.getWriter(writerId);
        createdPost.setWriter(writer);

        try {
            postController.createPost(createdPost);
            System.out.println("Post created");
        } catch (Exception e) {
            System.out.println("Error while creating post");
        }
    }

    @Override
    public void update() {
        read();
        System.out.println(EDIT_POST_MSG);
        Integer id = Integer.parseInt(scanner.nextLine());
        try{
            Post post = postController.getPost(id);
            post.setUpdated(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").
                    format(Calendar.getInstance().getTime()));
            System.out.println("Enter new content: ");
            String content = scanner.nextLine();
            post.setContent(content);

            System.out.println("Do you want to change Labels of this post? Y/N");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("y")){
                List<Label> postLabels = labelController.getLabels();
                if(postLabels != null){
                    postLabels.sort(Comparator.comparing(Label::getId));
                    postLabels.forEach(System.out::println);
                }
                System.out.println("Enter ID of label to add to the post:");
                List<Label> labels = new ArrayList<>();
                addLabelsToPost(labels);
                post.setPostLabels(labels);
            } else {
                System.out.println(" Do you want to change writer? Y/N");
                String nextAnswer = scanner.nextLine();
                if(nextAnswer.equalsIgnoreCase("y")){
                    List<Writer> writers = writerController.getWriters();
                    if (writers != null) {
                        writers.sort(Comparator.comparing(Writer::getId));
                        writers.forEach(System.out::println);
                    }
                    System.out.println("Enter writer ID: ");
                    Integer writerId = Integer.parseInt(scanner.nextLine());
                    Writer writer = writerController.getWriter(writerId);
                    post.setWriter(writer);
                }
            }
            postController.updatePost(post);
            System.out.println("Post updated: " + post);
        } catch (Exception e){
            System.out.println("Error while updating post");
        }

    }

    @Override
    public void delete() {
        read();
        System.out.println(DELETE_POST_MSG);
        Integer id = Integer.parseInt(scanner.nextLine());
        try{
            postController.deleteById(id);
            System.out.println("Post deleted");
        } catch (Exception e){
            System.out.println("Error while deleting post");
        }
    }

    @Override
    public void read() {
        List<Post> posts = null;
        try {
            posts = postController.getPosts();
        }
        catch (Exception e){
            System.out.println("Error loading posts");
        }
        System.out.println(PRINT_POST_LIST);
        if(posts != null){
            posts.sort(Comparator.comparing(Post::getId));
            posts.forEach(System.out::println);
        }

    }


    private void addLabelsToPost(List<Label> postLabels){
        boolean isContinue = true;
        while(isContinue) {
            Integer labelId = Integer.parseInt(scanner.nextLine());
            postLabels.add(labelController.getLabel(labelId));
            System.out.println("Do you want to add one more label? Y/N");
            if (scanner.nextLine().equalsIgnoreCase("n")){
                isContinue = false;
            } else {
                System.out.println("Enter id of label to add: ");
            }
        }
    }
}
