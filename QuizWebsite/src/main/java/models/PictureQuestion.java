package models;

/**
 * This class is designed to handle Picture-Response type questions.
 * It is a subclass of the SingleQuestion class,
 * as it is almost identical but has extra feature of having url of the img
 */
public class PictureQuestion extends SingleQuestion {

    private String imgUrl;

    /**
     * Constructor for the PictureQuestion class with a custom question and image URL.
     * @param question The text of the picture question.
     * @param imgUrl   The URL of the image associated with the question.
     */
    public PictureQuestion(String question, String imgUrl, String type) {
        super(question, type); // Call the constructor of the superclass (SingleQuestion) to set the question text
        this.imgUrl = imgUrl; // Set the URL of the image
    }

    /**
     * Constructor for the PictureQuestion class with a default question text "Name this image's content".
     * @param imgUrl The URL of the image associated with the question.
     */
    public PictureQuestion(String imgUrl, String type) {
        super("Name this image's content", type);
        this.imgUrl = imgUrl;
    }

    /**
     * Getter method to retrieve the URL of the image associated with the question.
     * @return A String representing the URL of the image.
     */
    public String getImgUrl() {
        return imgUrl;
    }

}
