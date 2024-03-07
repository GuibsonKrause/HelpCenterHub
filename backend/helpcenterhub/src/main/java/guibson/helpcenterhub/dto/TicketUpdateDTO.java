package guibson.helpcenterhub.dto;

import java.time.LocalDateTime;

public class TicketUpdateDTO {
    private Long id;
    private Long ticketId;
    private Long updatedBy;
    private String updateType;
    private String description;
    private LocalDateTime updatedAt;

    // Getters and Setters
    // Getters
    public Long getId() {
        return id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public String getUpdateType() {
        return updateType;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
