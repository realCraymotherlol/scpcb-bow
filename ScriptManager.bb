Include "AngelBB.bb"
Include "AngelCB.bb"

Type Hooks
    Field Subscribers%
    Field ID%
    Field FuncName$
End Type

Global HookCount% = 0

Delete Each Hooks
Global Initialize.Hooks = CreateHook("Hook_Initialize")
Global Update.Hooks = CreateHook("Hook_Update")
Global UpdateEvent.Hooks = CreateHook("Hook_UpdateEvent")
Global FillRoom.Hooks = CreateHook("Hook_FillRoom")
Global CreateNPC.Hooks = CreateHook("Hook_CreateNPC")
Global UpdateNPC.Hooks = CreateHook("Hook_UpdateNPC")

Dim HookFuncs%(HookCount, 0)

Function CreateHook.Hooks(funcName$)
    Local h.Hooks = New Hooks
    h\FuncName = funcName$
    h\ID = HookCount
    HookCount = HookCount + 1
    Return h
End Function

Function InitializeHooks(moduleCount%)
    For h.Hooks = Each Hooks
        h\Subscribers = 0
    Next
    Dim HookFuncs%(HookCount, moduleCount)
End Function

Function SubscribeModuleHooks(m%)
    For h.Hooks = Each Hooks
        Local f% = GetModuleFunction(m, h\FuncName)
        If f <> 0 Then
            HookFuncs%(h\ID, h\Subscribers) = f
            h\Subscribers = h\Subscribers + 1
        End If
    Next
End Function

Function CallHook(h.Hooks)
    For i = 0 To h\Subscribers-1
        ExecuteFunction(HookFuncs%(h\ID, i))
    Next
End Function

Function CreateModule%(name$, file$)
    BeginModule(name)
    ModuleAddFile(file$)
    EndModule()
    Return GetModule(name$)
End Function
