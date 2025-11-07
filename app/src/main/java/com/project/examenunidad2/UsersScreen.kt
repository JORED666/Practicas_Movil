package com.project.examenunidad2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// ------------------ ROOM ------------------

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAll(): Flow<List<UserEntity>>

    @Insert
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun clearAll()
}

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

// ------------------ UI ------------------

@Composable
fun UsersScreen(
    db: AppDatabase,
    onBack: () -> Unit
) {
    val dao = remember(db) { db.userDao() }
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var users by remember { mutableStateOf(emptyList<UserEntity>()) }

    LaunchedEffect(dao) {
        dao.getAll().collectLatest { users = it }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            "Usuarios (Room)",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(12.dp))

        ElevatedCard(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        val n = name.text.trim()
                        if (n.isNotEmpty()) {
                            scope.launch { dao.insert(UserEntity(name = n)) }
                            name = TextFieldValue("")
                        }
                    }) {
                        Text("Agregar")
                    }
                    OutlinedButton(onClick = { scope.launch { dao.clearAll() } }) {
                        Text("Limpiar todo")
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        ElevatedCard(Modifier.weight(1f).fillMaxWidth()) {
            if (users.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Sin usuarios guardados")
                }
            } else {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(users, key = { it.id }) { user ->
                        ListItem(
                            headlineContent = { Text(user.name) },
                            trailingContent = {
                                TextButton(onClick = {
                                    scope.launch { dao.delete(user) }
                                }) { Text("Eliminar") }
                            }
                        )
                        Divider()
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))
        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Volver")
        }
    }
}
