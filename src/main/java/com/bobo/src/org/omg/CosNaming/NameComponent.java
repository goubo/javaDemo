package org.omg.CosNaming;


/**
* org/omg/CosNaming/NameComponent.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /jenkins/workspace/8-2-build-macosx-x86_64/jdk8u271/605/corba/src/share/classes/org/omg/CosNaming/nameservice.idl
* Wednesday, September 16, 2020 4:54:59 PM GMT
*/

public final class NameComponent implements org.omg.CORBA.portable.IDLEntity
{
  public String id = null;
  public String kind = null;

  public NameComponent ()
  {
  } // ctor

  public NameComponent (String _id, String _kind)
  {
    id = _id;
    kind = _kind;
  } // ctor

} // class NameComponent
