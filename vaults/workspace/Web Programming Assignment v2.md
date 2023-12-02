Dogukan Celik
20200808071

A note custom component created. It interacts when clicked.

```ts
// note.component.ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})

export class NoteComponent {
  isNoted: boolean = false;
  addNote(): void {
    this.isNoted = !this.isNoted;
  }
}
```

```html
<!-- note.component.html -->
<button data-bs-toggle="modal" data-bs-target="#asd" [ngClass]="{'btn-success':isNoted}" (click)="addNote()"
  class="form-control btn btn-primary mt-1">
  <i [ngClass]="{'fa-bounce':isNoted}" class="fa-solid fa-note-sticky"></i>
</button>
```

