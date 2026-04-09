void Hook_InitializeEvents() {
    CreateEvent("guardspin", "room3pit", 0, 1);
}

void Hook_UpdateEvent(CB::Event@ e) {
    if (e.Name == "guardspin") {
        e.Room.Objects[1].Turn(0, FPSFactor * 10, 0);
    }
}

void Hook_FillRoom(CB::Room@ r) {
    if (r.Objects[1] == null) @r.Objects[1] = CB::NPC(NPC::Type::Guard, r.X, r.Y + 1, r.Z).Collider;
}

using namespace CB;
using namespace B3D;

float configuredFOV = -1.f;

void Hook_Update() {
    if (configuredFOV == -1.f) {
        configuredFOV = Options::FOV;
    }
    Options::FOV = configuredFOV * (1.f + Player::CurrentSpeed * 5);
}
