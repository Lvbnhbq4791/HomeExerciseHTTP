public class FactsAboutCats {

    private  String id;
    private  String text;
    private  String type;
    private  String user;
    private String upvotes;

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUpvotes(String upvotes) {
        this.upvotes = upvotes;
    }

    public String getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "\n  id-" + id+
                "\n  text-" + text +
                "\n  type-" + type +
                "\n  user-" + user +
                "\n  upvotes-" + upvotes;
    }
}
