package sites.linkedIn.model;

public class Person {
    private String name;
    private String description;
    private String pictureUrl;
    private String profileUrl;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;

        return true;
    }

    @Override
    public final int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final String getPictureUrl() {
        return pictureUrl;
    }

    public final void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public final String getProfileUrl() {
        return profileUrl;
    }

    public final void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
