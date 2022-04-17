package lesson4.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisles",
        "cost",
        "startDate",
        "endDate"
})

@Data
public class ResponseGetListItems {

    @JsonProperty("aisles")
    public List<Aisle> aisles = new ArrayList<>();
    @JsonProperty("cost")
    public Double cost;
    @JsonProperty("startDate")
    public Integer startDate;
    @JsonProperty("endDate")
    public Integer endDate;

}
