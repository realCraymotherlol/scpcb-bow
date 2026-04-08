void Hook_UpdateEvent(CB::Event@ e) {
    if (e.Name == "COOL") {
        B3D::DebugLog("AWESOME!");
    } else {
        //B3D::DebugLog("Awww" + e.Name);
    }
}

void Hook_FillRoom(CB::Room@ r) {
    B3D::DebugLog("AWESOME");
    CB::Decal@ d = CB::Decal(0, r.X, r.Y + 0.01, r.Z, 90, 0, 0);
}

void Hook_Update() {
    B3D::DebugLog("AWESOME");
}
