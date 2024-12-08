package app.example.data

// -------------------------------------------------------------------------------------
//
// THIS IS AN EXAMPLE FILE WITH REPOSITORY THAT IS INJECTED IN CIRCUIT SCREEN
//
// You should create your own repository and delete this file.
//
//  -------------------------------------------------------------------------------------

/**
 * Data class representing an email.
 */
data class Email(
    val id: String,
    val subject: String,
    val body: String,
    val sender: String,
    val timestamp: String,
    val recipients: List<String>,
)

/**
 * This is example repository. It is used to demonstrate how to use Dagger in Anvil.
 */
class ExampleEmailRepository {
    fun getEmails(): List<Email> =
        listOf(
            Email(
                id = "1",
                subject = "Meeting re-sched!",
                body = "Hey, I'm going to be out of the office tomorrow. Can we reschedule?",
                sender = "Ali Connors",
                timestamp = "3:00 PM",
                recipients = listOf("a@example.com"),
            ),
            Email(
                id = "2",
                subject = "Team retro",
                body = "Don't forget about the team retrospective meeting tomorrow. Lunch will be provided. Please RSVP.",
                sender = "John Doe",
                timestamp = "4:00 PM",
                recipients = listOf("b@example.com"),
            ),
        )

    fun getEmail(emailId: String): Email = getEmails().find { it.id == emailId } ?: throw IllegalArgumentException("Email not found")
}
