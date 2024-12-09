package vitaliifedyk.library.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vitaliifedyk.library.enumeration.SearchOperation;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
}
