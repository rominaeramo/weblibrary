package com.wiley.umltoolkit.casestudy.vo;
import com.wiley.umltoolkit.casestudy.vo.common.BaseVo;

/** TitleVo is a value object that contains data about a Title. A Title
 * represents one book/magazine to many items (or copies of the book/magazine)
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class TitleVo extends BaseVo  {

    private String titleId;
    private String name;
    private String author;
    private String isbn;
    private TitleKindVo titleKind;
    private String description;

    public TitleVo()  {
        titleKind = new TitleKindVo();
    }
    public void setName(String name)  {
        this.name = name;
    }
    public void setAuthor(String author)  {
        this.author = author;
    }
    public void setIsbn(String isbn)  {
        this.isbn = isbn;
    }
    public String getName()  {
        return name;
    }
    public String getAuthor()  {
        return author;
    }
    public String getIsbn()  {
        return isbn;
    }
    public String getDescription()  {
        return description;
    }
    public void setDescription(String description)  {
        this.description = description;
    }
    public TitleKindVo getTitleKind()  {
        return titleKind;
    }
    public void setTitleKind(TitleKindVo titleKind)  {
        this.titleKind = titleKind;
    }
    public String getTitleId()  {
        return titleId;
    }
    public void setTitleId(String titleId)  {
        this.titleId = titleId;
    }

}