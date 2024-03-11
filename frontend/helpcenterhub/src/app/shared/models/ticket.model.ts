export enum TicketStatus {
    OPEN = "OPEN",
    CLOSED = "CLOSED",
  }
  
  export class Ticket {
    constructor(
      public id: number,
      public userId: number,
      public subject: string,
      public description: string,
      public status?: TicketStatus,
      public createdAt?: Date,
      public closedAt?: Date
    ) {}
  }
  