package in.trois.stockmanagement.entity;

import in.trois.stockmanagement.constants.Status;
import in.trois.stockmanagement.payload.DropdownPayload;
import in.trois.stockmanagement.request.AbstractDto;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable {

    @CreatedBy
    @Column(name = "created_by")
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_at", nullable = false, insertable = false)
    protected Timestamp createdAt;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    protected Timestamp lastModifiedAt;

    @Column(name = "status", nullable = false)
    protected Integer status = Status.ALLOWED;

    public abstract <K extends AbstractDto> void copyFromDTO(K dto);

    public abstract <K extends AbstractDto> K toDTO();

    public DropdownPayload<?> toDropDownPayload() {
        return null;
    }

    public <K extends AbstractDto> K toMinDTO() {
        return null;
    }
}
