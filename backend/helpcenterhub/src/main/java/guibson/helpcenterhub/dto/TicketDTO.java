package guibson.helpcenterhub.dto;

public class TicketDTO {
    private Long userId;
    private String subject;
    private String description;

    // Getters e setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
