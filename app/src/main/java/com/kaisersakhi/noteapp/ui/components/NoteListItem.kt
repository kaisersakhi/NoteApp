package com.kaisersakhi.noteapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaisersakhi.noteapp.data.model.Note
import com.kaisersakhi.noteapp.R
import com.kaisersakhi.noteapp.ui.theme.DarkBlue
import com.kaisersakhi.noteapp.ui.theme.RobotoSlab

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteListItem(note: Note, onItemClick: (Int) -> Unit, onDelete : (Int) -> Unit) {
    Surface(
        onClick = { onItemClick.invoke(note.id) },
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 19.dp)
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.note),
                contentDescription = "",
                modifier = Modifier.size(40.dp)
                    .weight(1f)
            )

            Column(
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = note.noteTitle,
                    fontFamily = RobotoSlab,
                    fontSize = 25.sp,
                    maxLines = 1
                )
                Text(
                    text = note.noteContent,
                    fontFamily = FontFamily.Default,
                    maxLines = 2,
                    fontSize = 18.sp
                )
            }


            Image(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        onDelete.invoke(note.id)
                    }
                    .weight(1f)
                    .size(30.dp),
            )
        }
    }
}