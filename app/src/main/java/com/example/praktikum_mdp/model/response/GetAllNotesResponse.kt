import com.example.praktikum_mdp.model.response.NoteItem

data class NotesResponse(
    val code: Int,
    val message: String,
    val data: NotesListData
)

data class GetAllNotesResponse(
    val status: String,
    val data: NotesListData
)

data class NotesListData(
    val notes: List<NoteItem>
)
