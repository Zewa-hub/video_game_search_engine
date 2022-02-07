package fr.lernejo.fileinjector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameMap {
    final public String id;
    final public String title;
    final public String game_url;
    final public String thumbnail;
    final public String short_description;
    final public String genre;
    final public String platform;
    final public String publisher;
    final public String developer;
    final public String release_date;
    final public String freetogame_profile_url;
    
    @JsonCreator
    public GameMap (
        @JsonProperty(value = "id", required = true) String id,
        @JsonProperty(value = "title", required = true) String title,
        @JsonProperty(value = "game_url", required = false)String game_url,
        @JsonProperty(value = "thumbnail", required = true) String thumbnail,
        @JsonProperty(value = "short_description", required = true) String short_description,
        @JsonProperty(value = "genre", required = true) String genre,
        @JsonProperty(value = "platform", required = true) String platform,
        @JsonProperty(value = "publisher", required = true) String publisher,
        @JsonProperty(value = "developer", required = true) String developer,
        @JsonProperty(value = "release_date", required = true) String release_date,
        @JsonProperty(value = "freetogame_profile_url", required = false)String freetogame_profile_url
    )
    {
        this.id = id;
        this.title = title;
        this.game_url = game_url;
        this.thumbnail=thumbnail;
        this.short_description = short_description;
        this.genre=genre;
        this.platform = platform;
        this.publisher=publisher;
        this.developer=developer;
        this.release_date=release_date;
        this.freetogame_profile_url=freetogame_profile_url;
    }
}
